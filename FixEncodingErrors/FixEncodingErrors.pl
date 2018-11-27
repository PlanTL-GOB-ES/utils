#!/usr/bin/perl
#
# Usage: 
# 
#   FixEncodingErrors.pl [options] 
#
#

=head1 NAME

   FixEncodingErrors - A script to fix common encoding errors in corpora.

=head1 SYNOPSIS

 FixEncodingErrors.pl [options] 
  Help Options:
   --dir directory     Set root directory for your documents (Default: Documents/)
   --help              Show this scripts help information.
   --manual            Read this scripts manual.
	
=cut

=head1 OPTIONS

=over 8

=item B<--help>
Show the brief help information.

=item B<--manual>
Read the manual, with examples.

=back

=cut


=head1 EXAMPLES

  The following are examples of this script:
   $ FixEncodingErrors.pl 
   $ FixEncodingErrors.pl --dir my_corpus
  Note: Place all documents inside the given directory.

=cut


=head1 DESCRIPTION

  This script fixes some of the most common encoding problems in corpora.

=cut


=head1 AUTHOR

 Text Mining Unit
 --
 PlanTL.Sanidad@gmail.com

=cut

use warnings;
use strict;
use Getopt::Long;
use Pod::Usage;

my $ROOT_DIR = "Documents";

# Parse command line arguments.
parseCommandLineArguments();

my $args = "";

=head2 parseCommandLineArguments

  Parse the arguments specified upon the command line.

=cut

## Define common errors and its correspondence.
my %encoding_errors = ('' => '\'',
		       '' => '\'',
		       '' => '\'',
		       '' => '\'',
		       '' => '-',
		       '' => '-',
		       '' => '-',
		       '' => ' ');

## Fix encoding errors in corpus
cleanCorpus();

################################
######### SUB-ROUTINES #########
################################

sub cleanCorpus {
    foreach my $encoding_error (keys %encoding_errors) {
	system("for each in \$(find $ROOT_DIR -type f) ; do sed -i \"s/$encoding_error/$encoding_errors{$encoding_error}/g\" \$each ; done")
    }
}

sub parseCommandLineArguments
{
    my $HELP    = 0;   # Show help overview.
    my $MANUAL    = 0;   # Show manual

    #  Parse options.
    #
    GetOptions(
           "dir=s",        \$ROOT_DIR,
           "help",         \$HELP,
           "manual",       \$MANUAL,
          );
    
    pod2usage(1) if $HELP;
    pod2usage(-verbose => 2 ) if $MANUAL;

    pod2usage if $#ARGV >0 ;

}
